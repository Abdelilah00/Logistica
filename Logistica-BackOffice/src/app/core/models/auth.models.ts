import {BaseModel} from './base-model.model';

export class User {
  id: number;
  username: string;
  password: string;
  firstName?: string;
  lastName?: string;
  token?: string;
  email: string;
}


export class Input extends BaseModel {

  supplierName: string;
  date: string;
  description: string;
  transactionDetails: TransactionDetail[];
}


export class TransactionDetail extends BaseModel {
  article: number;
  inputId?: number;
  outputId?: number;
  lot: number;
  qte: number;
  product: Product;
}

export class Product extends BaseModel {
  categoryId: number;
  expDate: Date;
  name: string;
  priceHT: number;
  stockMax: number;
  stockMin: number;
  stockSecurity: number;
  tva: number;
}

export class BreadCrumb {
  title: string;
  items: Array<{ label: string; path?: string; active?: boolean; }>;
}
