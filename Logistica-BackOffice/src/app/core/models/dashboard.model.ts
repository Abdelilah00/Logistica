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


export interface Transaction {
  orderid: string;
  date: string;
  billingname: string;
  total: string;
  paymentstatus: string;
}

export class SeriesList {
  items: SeriesItem[];
  kpi: string;
}

export class SeriesItem {
  time: number;
  value: number;
}

export class SeriesPredList {
  items: SeriesItem[];
  min: number;
  med: number;
  max: number;
}

export class TreeMapItem {
  time: number;
  value: number;
  children: TreeMapItem[];
}

export class Statistic {
  kpi: string;
  value: number;
  growth: number;
  hidden = false;
  appendToChart = false;
}
