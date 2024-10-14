import { Borne } from "./Borne";

export type Lieu = {
  id: number;
  numero: string;
  rue: string;
  ville: string;
  codePostal: string;
  latitude: number;
  longitude: number;
  bornes: Borne[];
};
