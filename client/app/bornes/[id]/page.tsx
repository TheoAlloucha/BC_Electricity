"use client";

import { Borne } from "@/types/Borne";
import Image from "next/image";
import Link from "next/link";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

async function fetchData(id: string) {
  return await fetch(`http://localhost:8180/api/bornes/${id}`).then((r) =>
    r.json()
  );
}

function Page() {
  const [borne, setBorne] = useState<Borne>();
  const { id } = useParams();

  useEffect(() => {
    fetchData(id as string).then((data: Borne) => {
      setBorne(data);
    });
  }, [id]);

  return (
    <div className="w-full mt-3">
      <button className="btn">
        {" "}
        <Link href="/">Retour à la page principale</Link>
      </button>

      <div className="card card-side bg-base-100 shadow-xl mt-3">
        <figure>
          <Image
            width={1000}
            height={100}
            src={
              borne?.photo
                ? borne.photo
                : "https://img.daisyui.com/images/stock/photo-1635805737707-575885ab0820.webp"
            }
            alt="Movie"
            className="w-full"
          />
        </figure>
        <div className="card-body">
          <h2 className="card-title">{borne?.nom}</h2>
          <div>
            <p>instructions : {borne?.instructions ?? "Aucune instruction"}</p>
            <p>tauxHoraire : {borne?.tauxHoraire} €/h</p>
            <p>puissance : {borne?.puissance} kvn</p>
            <p>Sur pied : {borne?.onPied ? "Oui" : "Non"}</p>
            <button className="btn btn-primary mt-3">Louer</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Page;
