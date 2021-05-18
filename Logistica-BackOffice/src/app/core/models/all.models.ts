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
  ref: string;
  supplierName: string;
  date: string;
  description: string;
  transactionDetails: TransactionDetail[];
}

export class Output extends BaseModel {
  ref: string;
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
  expDate: Date;
  tVa: number;
  priceHT: number;
  productName: string;
  productId: number;
}

export class Category extends BaseModel {
  name: string;
}

export class Product extends BaseModel {
  category: Category;
  expDate: Date;
  name: string;
  stockMax: number;
  stockMin: number;
  stockSecurity: number;
}

export class Supplier extends BaseModel {
  name: string;
  adresse: string;
  nRCommerce: string;
  contactPhone: string;
  contactWebSite: string;
  contactEmail: string;
  sectorName: string;
  bankName: string;
  bankCode: string;
  bankAccountNumber: string;
  bankRIB: string;
}

export class Parameter extends BaseModel {
  name: string;
}

export class Stock extends BaseModel {
  name: string;
  adresse: string;
  area: number;
  responsibleName: string;
  responsibleId: number;
}


class StockType extends BaseModel {
  name: string;
}

export class BreadCrumb {
  title: string;
  items: Array<{ label: string; path?: string; active?: boolean; }>;
}
