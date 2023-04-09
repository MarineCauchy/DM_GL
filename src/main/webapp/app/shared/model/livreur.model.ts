import { ICommande } from '@/shared/model/commande.model';
import { IAssociation } from '@/shared/model/association.model';

export interface ILivreur {
  id?: number;
  idL?: number;
  nomL?: string;
  prenomL?: string;
  telephoneL?: string;
  commandes?: ICommande[] | null;
  association?: IAssociation | null;
}

export class Livreur implements ILivreur {
  constructor(
    public id?: number,
    public idL?: number,
    public nomL?: string,
    public prenomL?: string,
    public telephoneL?: string,
    public commandes?: ICommande[] | null,
    public association?: IAssociation | null
  ) {}
}
