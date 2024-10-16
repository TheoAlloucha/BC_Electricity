"use client";

import { Borne } from "@/types/Borne";
import { Lieu } from "@/types/Lieu";
import { Values } from "@/types/Value";
import "leaflet/dist/leaflet.css";
import dynamic from "next/dynamic";
import { useEffect, useState } from "react";
import AutoCompleteForm from "../../components/AutoCompleteForm"; // Import the AutoCompleteForm component

const MapComponent = dynamic(() => import("../../components/Map"), {
  ssr: false,
});

function App() {
  const [markers, setMarkers] = useState<Lieu[]>([]);
  const [mapPosition, setMapPosition] = useState<[number, number] | null>(null);

  // Fetch markers when the component mounts
  useEffect(() => {
    const fetchMarkers = async () => {
      const response = await fetch("http://localhost:8180/api/lieux/");
      const lieux: Lieu[] = await response.json();
      const array: Lieu[] = [];

      lieux.forEach((lieu: Lieu) => {
        lieu.bornes.forEach((borne: Borne) => {
          array.push({ ...lieu, ...borne });
        });
      });

      setMarkers(array);
    };

    fetchMarkers();
  }, []);

  const handlePlaceSelect = (value: {
    geometry: { coordinates: [number, number] };
  }) => {
    setMapPosition([
      value.geometry.coordinates[1],
      value.geometry.coordinates[0],
    ]);
  };

  return (
    <>
      <div className="w-screen h-full">
        <div className="flex flex-col items-center m-3">
          <div className="pb-[100px]">
            <AutoCompleteForm
              onPlaceSelect={(value: Values) => {
                if (value.geometry) {
                  handlePlaceSelect(
                    value as { geometry: { coordinates: [number, number] } }
                  );
                }
              }}
            />
          </div>
        </div>
        <div className="grid grid-cols-1">
          <div>
            <MapComponent markers={markers} position={mapPosition} />
          </div>
        </div>
      </div>
    </>
  );
}

export default App;
