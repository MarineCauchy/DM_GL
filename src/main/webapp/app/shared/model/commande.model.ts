import { IClient } from '@/shared/model/client.model';
import { IProduit } from '@/shared/model/produit.model';
import { ILivreur } from '@/shared/model/livreur.model';

export interface ICommande {
  id?: number;
  idCo?: number;
  prixTotal?: number;
  date?: Date;
  adresseLivraison?: string;
  client?: IClient | null;
  produit?: IProduit | null;
  livreur?: ILivreur | null;
}

export class Commande implements ICommande {
  constructor(
    public id?: number,
    public idCo?: number,
    public prixTotal?: number,
    public date?: Date,
    public adresseLivraison?: string,
    public client?: IClient | null,
    public produit?: IProduit | null,
    public livreur?: ILivreur | null
  ) {}
}
