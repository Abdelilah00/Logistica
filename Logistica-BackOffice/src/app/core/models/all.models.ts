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

export class Transfer extends BaseModel {
  ref: string;
  actorId: number;
  actorName: string;
  date: string;
  description: string;
  fromStockId: number;
  fromStockName: string;
  toStockId: number;
  toStockName: string;
  transactionDetails: TransactionDetail[];
}

export class TransferDetails extends BaseModel {
  productId: number;
  productName: string;
  qte: number;
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
  stockId: number;
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

  qteInStocks: number;
  qteByStock: number;
}

export class Stock extends BaseModel {
  name: string;
  adresse: string;
  area: number;
  responsibleName: string;
  responsibleId: number;

  qteByProduct: number;
  qteOfProducts: number;
}

export class Actor extends BaseModel {
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

export class Service extends BaseModel {
  name: string;
}

export class Parameter extends BaseModel {
  name: string;
}


class StockType extends BaseModel {
  name: string;
}

export class BreadCrumb {
  title: string;
  items: Array<{ label: string; path?: string; active?: boolean; }>;
}
