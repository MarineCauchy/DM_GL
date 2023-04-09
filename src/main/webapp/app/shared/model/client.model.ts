import { IProduit } from '@/shared/model/produit.model';
import { ICommande } from '@/shared/model/commande.model';

export interface IClient {
  id?: number;
  idC?: number;
  nomC?: string;
  prenomC?: string;
  adresseC?: string;
  emailC?: string;
  telephoneC?: string;
  produits?: IProduit[] | null;
  commandes?: ICommande[] | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public idC?: number,
    public nomC?: string,
    public prenomC?: string,
    public adresseC?: string,
    public emailC?: string,
    public telephoneC?: string,
    public produits?: IProduit[] | null,
    public commandes?: ICommande[] | null
  ) {}
}
