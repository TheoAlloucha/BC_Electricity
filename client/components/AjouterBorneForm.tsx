// AjouterBorneForm.tsx
"use client";

import { Lieu } from "@/types/Lieu";
import { BorneFormData, borneSchema } from "@/utils/schema";
import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { toast } from "sonner";

export default function AjouterBorneForm() {
  const [lieuxOptions, setLieuxOptions] = useState<Lieu[]>([]);

  useEffect(() => {
    const fetchLieux = async () => {
      try {
        const response = await fetch("http://localhost:8180/api/lieux/", {
          headers: {
            "Content-Type": "application/json",
          },
        });
        if (!response.ok) {
          throw new Error(`Erreur HTTP ! statut : ${response.status}`);
        }
        const data = await response.json();
        setLieuxOptions(data);
        console.log(data);
      } catch (error) {
        console.error("Erreur lors de la récupération des lieux:", error);
      }
    };

    fetchLieux();
  }, []);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<BorneFormData>({
    resolver: zodResolver(borneSchema),
  });

  const onSubmit = (data: BorneFormData) => {
    console.log("Données de la Borne :", data);
    fetch("http://localhost:8180/api/bornes/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nom: data.nom,
        instruction: data.instructions,
        tauxHoraire: data.tauxHoraire,
        photo: data.photo,
        video: data.video,
        puissance: data.puissance,
        lieu: {
          id: data.lieuId,
        },
      }),
    }).then((response) => {
      if (response.ok) {
        toast.success("Borne ajouté avec succès !");
      } else {
        toast.error("Erreur dans l'ajout de la borne !");
      }
    });
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="max-w-lg mx-auto p-6 bg-base-200 rounded-lg shadow"
    >
      <h2 className="text-2xl font-bold mb-6">Ajouter une Borne</h2>

      {/* Sélection du Lieu */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Lieu :</span>
        </label>
        <select
          className="select select-bordered w-full focus:ring focus:ring-primary"
          {...register("lieuId", { valueAsNumber: true })}
        >
          <option value="">Sélectionnez un Lieu</option>
          {lieuxOptions.map((lieu) => (
            <option key={lieu.id} value={lieu.id}>
              {lieu.numero} {lieu.rue} {lieu.ville}, {lieu.codePostal}
            </option>
          ))}
        </select>
        {errors.lieuId && (
          <span className="text-error">{errors.lieuId.message}</span>
        )}
      </div>

      {/* Champ Nom */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Nom :</span>
        </label>
        <input
          type="text"
          placeholder="Entrez le nom"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("nom")}
        />
        {errors.nom && <span className="text-error">{errors.nom.message}</span>}
      </div>

      {/* Champ Instructions */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Instructions :</span>
        </label>
        <textarea
          placeholder="Entrez les instructions"
          className="textarea textarea-bordered w-full focus:ring focus:ring-primary"
          {...register("instructions")}
        ></textarea>
        {errors.instructions && (
          <span className="text-error">{errors.instructions.message}</span>
        )}
      </div>

      {/* Champ Taux Horaire */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Taux Horaire en € :</span>
        </label>
        <input
          type="number"
          step="any"
          placeholder="0.00"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("tauxHoraire", { valueAsNumber: true })}
        />
        {errors.tauxHoraire && (
          <span className="text-error">{errors.tauxHoraire.message}</span>
        )}
      </div>

      {/* Champ Photo */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Photo :</span>
        </label>
        <input
          type="text"
          placeholder="URL de la photo"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("photo")}
        />
        {errors.photo && (
          <span className="text-error">{errors.photo.message}</span>
        )}
      </div>

      {/* Champ Vidéo */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Vidéo :</span>
        </label>
        <input
          type="text"
          placeholder="URL de la vidéo"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("video")}
        />
        {errors.video && (
          <span className="text-error">{errors.video.message}</span>
        )}
      </div>

      {/* Champ Puissance */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Puissance :</span>
        </label>
        <input
          type="number"
          step="any"
          placeholder="Puissance en kW"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("puissance", { valueAsNumber: true })}
        />
        {errors.puissance && (
          <span className="text-error">{errors.puissance.message}</span>
        )}
      </div>

      {/* Champ Sur pied */}
      <div className="form-control mb-6">
        <label className="label cursor-pointer">
          <span className="label-text">Sur pied :</span>
          <input
            type="checkbox"
            className="toggle toggle-primary"
            {...register("onPied")}
          />
        </label>
        {errors.onPied && (
          <span className="text-error">{errors.onPied.message}</span>
        )}
      </div>

      {/* Bouton de soumission */}
      <button type="submit" className="btn btn-primary w-full">
        Ajouter la Borne
      </button>
    </form>
  );
}
