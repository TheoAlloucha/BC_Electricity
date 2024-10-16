import { z } from "zod";

export const lieuSchema = z.object({
  numero: z.string().min(1, { message: "Le numéro est requis" }),
  rue: z.string().min(1, { message: "La rue est requise" }),
  ville: z.string().min(1, { message: "La ville est requise" }),
  codePostal: z.string().min(1, { message: "Le code postal est requis" }),
  latitude: z
    .number({ invalid_type_error: "La latitude doit être un nombre" })
    .min(-90, { message: "Latitude invalide" })
    .max(90, { message: "Latitude invalide" }),
  longitude: z
    .number({ invalid_type_error: "La longitude doit être un nombre" })
    .min(-180, { message: "Longitude invalide" })
    .max(180, { message: "Longitude invalide" }),
});

export type LieuFormData = z.infer<typeof lieuSchema>;

export const borneSchema = z.object({
  lieuId: z.number({ invalid_type_error: "Le Lieu ID doit être un nombre" }),
  nom: z.string().min(1, { message: "Le nom est requis" }),
  instructions: z.string().optional(),
  tauxHoraire: z
    .number({ invalid_type_error: "Le taux horaire doit être un nombre" })
    .nonnegative({ message: "Le taux horaire doit être positif" }),
  photo: z.string().optional(),
  video: z.string().optional(),
  puissance: z
    .number({ invalid_type_error: "La puissance doit être un nombre" })
    .nonnegative({ message: "La puissance doit être positive" }),
  onPied: z.boolean(),
});

export type BorneFormData = z.infer<typeof borneSchema>;
