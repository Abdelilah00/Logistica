export interface ChartType {
  series?: any;
  plotOptions?: any;
  stroke?: any;
  dataLabels?: any;
  chart?: any;
  legend?: any;
  colors?: any;
  labels?: any;
  noData?: any;
}

export interface Stat {
  title: string;
  icon: string;
  value: string;
}

export interface Chat {
  id?: number;
  name?: string;
  message?: string;
  image?: string;
  time?: string;
  align?: string;
  text?: string;
}

export interface Transaction {
  orderid: string;
  date: string;
  billingname: string;
  total: string;
  paymentstatus: string;
}

export class SeriesList {
  items: ItemOfSeries[];
  kpi: string;
}

class ItemOfSeries {
  time: number;
  value: number;
}

export class Statistic {
  kpi: string;
  value: number;
  growth: number;
  hidden = false;
  appendToChart = false;
}
