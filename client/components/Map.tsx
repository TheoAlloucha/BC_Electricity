"use client";
import { Borne } from "@/types/Borne";
import { Lieu } from "@/types/Lieu";
import "leaflet-defaulticon-compatibility";
import "leaflet-defaulticon-compatibility/dist/leaflet-defaulticon-compatibility.webpack.css"; // Re-uses images from ~leaflet package
import "leaflet/dist/leaflet.css";
import { useEffect, useRef, useState } from "react";
import {
  MapContainer,
  Marker,
  Popup,
  TileLayer,
  useMapEvent,
} from "react-leaflet";

interface SetViewOnClickProps {
  animateRef: React.RefObject<boolean>;
}

function SetViewOnClick({ animateRef }: SetViewOnClickProps) {
  const map = useMapEvent("click", (e) => {
    map.setView(e.latlng, map.getZoom(), {
      animate: animateRef.current || false,
    });
  });

  return null;
}

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const MapComponent = (props: any) => {
  const animateRef = useRef(false);
  const [position, setPosition] = useState<[number, number] | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [isClient, setIsClient] = useState<boolean>(false);
  const [markers, setMarkers] = useState([]);

  useEffect(() => {
    // Détermine si l'on est du côté client
    setIsClient(true);
    setMarkers(props.markers);
  }, [props.markers]);

  useEffect(() => {
    if (isClient) {
      const handleGeolocationSuccess = (pos: GeolocationPosition) => {
        const { latitude, longitude } = pos.coords;
        setPosition([latitude, longitude]);
      };

      const handleGeolocationError = (error: GeolocationPositionError) => {
        setError("Impossible de récupérer votre position.");
        console.error(error);
      };

      navigator.geolocation.getCurrentPosition(
        handleGeolocationSuccess,
        handleGeolocationError
      );
    }
  }, [isClient]);

  if (error) {
    return <div>{error}</div>; // Afficher le message d'erreur
  }

  return (
    <>
      {position && markers ? (
        <MapContainer
          center={position || [51.505, -0.09]}
          zoom={13}
          scrollWheelZoom={true}
        >
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />

          {Array.isArray(markers) && markers.length > 0 ? (
            markers.map((lieu: Lieu) => (
              <Marker key={lieu.id} position={[lieu.longitude, lieu.latitude]}>
                <Popup>
                  {lieu.numero + " " + lieu.rue + " " + lieu.ville}
                  {lieu.bornes.map((borne: Borne) => {
                    return (
                      <p key={borne.nom}>
                        {borne.nom} - {borne.tauxHoraire}$
                      </p>
                    );
                  })}
                </Popup>
              </Marker>
            ))
          ) : (
            <p>Aucun marqueur à afficher.</p>
          )}

          <Marker position={position}>
            <Popup>
              Vous êtes ici : {position[0].toFixed(5)}, {position[1].toFixed(5)}
            </Popup>
          </Marker>
          <SetViewOnClick animateRef={animateRef} />
        </MapContainer>
      ) : (
        <p>Chargement de votre position...</p>
      )}
    </>
  );
};

export default MapComponent;
