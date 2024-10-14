"use client";
import { Values } from "@/types/Value";
import "@geoapify/geocoder-autocomplete/styles/minimal.css";
import {
  GeoapifyContext,
  GeoapifyGeocoderAutocomplete,
} from "@geoapify/react-geocoder-autocomplete";

interface AutoCompleteFormProps {
  onPlaceSelect: (value: Values) => void;
}

function AutoCompleteForm({ onPlaceSelect }: AutoCompleteFormProps) {
  return (
    <GeoapifyContext apiKey={process.env.NEXT_PUBLIC_GEOAPIFY_API_KEY}>
      <GeoapifyGeocoderAutocomplete
        placeholder="Entrer votre adresse"
        // Pass the onPlaceSelect function to placeSelect prop
        placeSelect={onPlaceSelect}
      />
    </GeoapifyContext>
  );
}

export default AutoCompleteForm;
