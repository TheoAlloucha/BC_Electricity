"use client";

import { LieuFormData, lieuSchema } from "@/utils/schema";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { toast } from "sonner";

export default function AjouterLieuForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LieuFormData>({
    resolver: zodResolver(lieuSchema),
  });

  const onSubmit = (data: LieuFormData) => {
    console.log("Données du Lieu :", data);
    fetch("http://localhost:8180/api/lieux/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    }).then((response) => {
      if (response.ok) {
        toast.success("Lieu ajouté avec succès !");
      } else {
        toast.error("Erreur dans l'ajout du lieu !");
      }
    });
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="max-w-lg mx-auto p-6 bg-base-200 rounded-lg shadow"
    >
      <h2 className="text-2xl font-bold mb-6">Ajouter un Lieu</h2>

      {/* Champ Numéro */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Numéro :</span>
        </label>
        <input
          type="text"
          placeholder="Entrez le numéro"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("numero")}
        />
        {errors.numero && (
          <span className="text-error">{errors.numero.message}</span>
        )}
      </div>

      {/* Champ Rue */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Rue :</span>
        </label>
        <input
          type="text"
          placeholder="Entrez la rue"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("rue")}
        />
        {errors.rue && <span className="text-error">{errors.rue.message}</span>}
      </div>

      {/* Champ Ville */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Ville :</span>
        </label>
        <input
          type="text"
          placeholder="Entrez la ville"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("ville")}
        />
        {errors.ville && (
          <span className="text-error">{errors.ville.message}</span>
        )}
      </div>

      {/* Champ Code Postal */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Code Postal :</span>
        </label>
        <input
          type="text"
          placeholder="Entrez le code postal"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("codePostal")}
        />
        {errors.codePostal && (
          <span className="text-error">{errors.codePostal.message}</span>
        )}
      </div>

      {/* Champ Latitude */}
      <div className="form-control mb-4">
        <label className="label">
          <span className="label-text">Latitude :</span>
        </label>
        <input
          type="number"
          step="any"
          placeholder="Latitude"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("latitude", { valueAsNumber: true })}
        />
        {errors.latitude && (
          <span className="text-error">{errors.latitude.message}</span>
        )}
      </div>

      {/* Champ Longitude */}
      <div className="form-control mb-6">
        <label className="label">
          <span className="label-text">Longitude :</span>
        </label>
        <input
          type="number"
          step="any"
          placeholder="Longitude"
          className="input input-bordered w-full focus:ring focus:ring-primary"
          {...register("longitude", { valueAsNumber: true })}
        />
        {errors.longitude && (
          <span className="text-error">{errors.longitude.message}</span>
        )}
      </div>

      {/* Bouton de soumission */}
      <button type="submit" className="btn btn-primary w-full">
        Ajouter le Lieu
      </button>
    </form>
  );
}
