import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild} from '@angular/core';
import {
  ApexAnnotations,
  ApexAxisChartSeries,
  ApexChart,
  ApexDataLabels,
  ApexFill,
  ApexGrid,
  ApexLegend,
  ApexNonAxisChartSeries,
  ApexPlotOptions,
  ApexResponsive,
  ApexStates,
  ApexStroke,
  ApexTheme,
  ApexTitleSubtitle,
  ApexTooltip,
  ApexXAxis,
  ApexYAxis,
  ChartComponent
} from 'ng-apexcharts';
import {ChartRange} from '../../models/indicator';

export type ChartOptions = {
  chart: ApexChart;
  annotations?: ApexAnnotations;
  colors?: string[];
  dataLabels?: ApexDataLabels;
  series: ApexAxisChartSeries | ApexNonAxisChartSeries;
  stroke?: ApexStroke;
  labels?: string[];
  legend?: ApexLegend;
  fill?: ApexFill;
  tooltip?: ApexTooltip;
  plotOptions?: ApexPlotOptions;
  responsive?: ApexResponsive[];
  xaxis: ApexXAxis | undefined;
  yaxis?: ApexYAxis | ApexYAxis[];
  grid?: ApexGrid;
  states?: ApexStates;
  title: ApexTitleSubtitle;
  subtitle?: ApexTitleSubtitle;
  theme?: ApexTheme;
};

@Component({
  selector: 'app-trading-chart',
  templateUrl: './trading-chart.component.html',
  styleUrls: ['./trading-chart.component.scss']
})
export class TradingChartComponent implements OnInit, OnChanges {

  @Input('data') chartData: [number, (number | null)][] = [];

  @Input('height') height: number | string| null = 220;

  @Output() activeRangeChanged: EventEmitter<ChartRange> = new EventEmitter();

  @ViewChild("chart", {static: false}) chart: ChartComponent = new ChartComponent();
  public chartOptions: Partial<ChartOptions> | any = {};
  public activeOptionButton: string = '1m';
  public updateOptionsData: any = {
    '1m': {
      xaxis: {
        min: new Date().getTime() - 1 * 30 * 24 * 60 * 60 * 1000,
        max: new Date().getTime()
      }
    },
    '6m': {
      xaxis: {
        min: new Date().getTime() - 6 * 30 * 24 * 60 * 60 * 1000,
        max: new Date().getTime()
      }
    },
    '1y': {
      xaxis: {
        min: new Date().getTime() - 12 * 30 * 24 * 60 * 60 * 1000,
        max: new Date().getTime()
      }
    },
    '5y': {
      xaxis: {
        min: new Date().getTime() - 5 * 12 * 30 * 24 * 60 * 60 * 1000,
        max: new Date().getTime()
      }
    }
  };

  constructor() {

  }

  ngOnInit(): void {
    this.initChart();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['chartData'].previousValue) {
      let updateSeries: ApexAxisChartSeries = [{
        data: this.chartData
      }];
      this.chart.updateSeries(updateSeries, true);
      this.chart.updateOptions(this.updateOptionsData[this.activeOptionButton], false, true, true);
    }
  }



  initChart(): void {
    this.chartOptions = {
      series: [
        {
          data: this.chartData
        }
      ],
      chart: {
        type: "area",
        height: this.height
      },
      annotations: {
        // yaxis: [
        //   {
        //     y: 30,
        //     borderColor: "#999",
        //     label: {
        //       text: "Support",
        //       style: {
        //         color: "#fff",
        //         background: "#00E396"
        //       }
        //     }
        //   }
        // ],
        // xaxis: [
        //   {
        //     x: new Date("14 Nov 2012").getTime(),
        //     borderColor: "#999",
        //     label: {
        //       text: "Rally",
        //       style: {
        //         color: "#fff",
        //         background: "#775DD0"
        //       }
        //     }
        //   }
        // ]
      },
      dataLabels: {
        enabled: false
      },
      xaxis: {
        type: "datetime",
        // min: new Date("01 Mar 2020").getTime(),
        // tickAmount: 6
      },
      tooltip: {
        x: {
          format: "dd MMM yyyy"
        }
      },
      fill: {
        type: "gradient",
        gradient: {
          shadeIntensity: 1,
          opacityFrom: 0.7,
          opacityTo: 0.9,
          stops: [0, 100]
        }
      }
    };
  }

  public updateOptions(option: string): void {
    this.activeOptionButton = option;
    let value: ChartRange = ChartRange._1_MONTH;
    if (option === '1m') {
      value = ChartRange._1_MONTH;
    } else if (option === '6m') {
      value = ChartRange._6_MONTH;
    } else if (option === '1y') {
      value = ChartRange._1_YEAR;
    } else if (option === '5y') {
      value = ChartRange._5_YEAR;
    }
    this.activeRangeChanged.emit(value);
  }
}
