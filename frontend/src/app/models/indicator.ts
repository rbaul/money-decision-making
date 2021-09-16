export interface IndicatorDetails {
  id: string;
  name: string;
  description: string;
  tradingAdapterName: String;
}

export interface IndicatorChartData {
  id: string;
  name: string;
  description: string;
  data: number[];
  timestamp: number[];
}

export enum ChartInterval {
  _1_DAY = '_1_DAY'
}

export enum ChartRange {
  _5_DAY = '_5_DAY',
  _1_MONTH = '_1_MONTH',
  _3_MONTH = '_3_MONTH',
  _6_MONTH = '_6_MONTH',
  _1_YEAR = '_1_YEAR',
  _2_YEAR = '_2_YEAR',
  _5_YEAR = '_5_YEAR',
  _10_YEAR ='_10_YEAR'
}
