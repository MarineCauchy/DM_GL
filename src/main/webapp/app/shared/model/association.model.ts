import { ILivreur } from '@/shared/model/livreur.model';

export interface IAssociation {
  id?: number;
  idA?: number;
  nomA?: string;
  livreurs?: ILivreur[] | null;
}

export class Association implements IAssociation {
  constructor(public id?: number, public idA?: number, public nomA?: string, public livreurs?: ILivreur[] | null) {}
}
