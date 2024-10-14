"use client";

import { Borne } from "@/types/Borne";
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
    <div className="w-full">
      <Link href="/">Retour à la page principale</Link>
      <div className="flex flex-col items-center m-3">
        <h1>Borne id : {id}</h1>
        <p>Nom : {borne?.nom}</p>
        <p>instructions : {borne?.instructions ?? "Aucune instruction"}</p>
        <p>tauxHoraire : {borne?.tauxHoraire} €/h</p>
        <p>puissance : {borne?.puissance} kvn</p>
        <p>onPied : {borne?.onPied ? "Oui" : "Non"}</p>
        <p>photo : {borne?.photo ?? "Aucune photo"}</p>
        <p>video : {borne?.video ?? "Aucun video"}</p>
      </div>
    </div>
  );
}

export default Page;
