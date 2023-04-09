import { IProduit } from '@/shared/model/produit.model';

export interface IRestaurant {
  id?: number;
  nom?: string;
  adresse?: string;
  produits?: IProduit[] | null;
}

export class Restaurant implements IRestaurant {
  constructor(public id?: number, public nom?: string, public adresse?: string, public produits?: IProduit[] | null) {}
}
