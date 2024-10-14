import { Borne } from "@/types/Borne";
import { Lieu } from "@/types/Lieu";
import "leaflet/dist/leaflet.css";
import dynamic from "next/dynamic";

const MapComponent = dynamic(() => import("../components/Map"), { ssr: false });

async function AllMarkers() {
  const array: Lieu[] = [];

  const lieux = await fetch("http://localhost:8180/api/lieux/").then((r) =>
    r.json()
  );

  lieux.forEach((lieu: Lieu) => {
    lieu.bornes.forEach((borne: Borne) => {
      array.push({ ...lieu, ...borne });
    });
  });
  return array;
}

async function App() {
  const markers = await AllMarkers();

  return (
    <>
      <div className="w-screen">
        <div className="grid grid-cols-2">
          <div>
            <MapComponent markers={markers} />
          </div>
          <span>a</span>
        </div>
      </div>
    </>
  );
}

export default App;
