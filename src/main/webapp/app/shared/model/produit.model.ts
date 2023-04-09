import { ICommande } from '@/shared/model/commande.model';
import { IClient } from '@/shared/model/client.model';
import { IRestaurant } from '@/shared/model/restaurant.model';

export interface IProduit {
  id?: number;
  nom?: string;
  prix?: number;
  commande?: ICommande | null;
  client?: IClient | null;
  restaurant?: IRestaurant | null;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public nom?: string,
    public prix?: number,
    public commande?: ICommande | null,
    public client?: IClient | null,
    public restaurant?: IRestaurant | null
  ) {}
}
