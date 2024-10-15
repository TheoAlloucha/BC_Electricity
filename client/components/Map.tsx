"use client";
import { Borne } from "@/types/Borne";
import { Lieu } from "@/types/Lieu";
import "leaflet-defaulticon-compatibility";
import "leaflet-defaulticon-compatibility/dist/leaflet-defaulticon-compatibility.webpack.css"; // Re-uses images from ~leaflet package
import "leaflet/dist/leaflet.css";
import Link from "next/link";
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

const MapComponent = ({
  markers,
  position,
}: {
  markers: Lieu[];
  position: [number, number] | null;
}) => {
  const animateRef = useRef(false);
  const [error] = useState<string | null>(null);
  const [isClient, setIsClient] = useState<boolean>(false);

  useEffect(() => {
    setIsClient(true);
  }, []);

  useEffect(() => {
    if (isClient && position) {
    }
  }, [isClient, position]);

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <>
      {position ? (
        <MapContainer center={position} zoom={13} scrollWheelZoom={true}>
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />

          {Array.isArray(markers) && markers.length > 0 ? (
            markers.map((lieu: Lieu) => (
              <Marker key={lieu.id} position={[lieu.latitude, lieu.longitude]}>
                <Popup>
                  <div className="flex flex-col gap-2">
                    {lieu.numero + " " + lieu.rue + " " + lieu.ville}
                    {lieu.bornes.map((borne: Borne) => {
                      return (
                        <Link key={borne.id} href={`/bornes/${borne.id}`}>
                          {borne.nom} - {borne.puissance}kvh -{" "}
                          {borne.tauxHoraire}€
                        </Link>
                      );
                    })}
                  </div>
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
        ""
      )}
    </>
  );
};

export default MapComponent;
