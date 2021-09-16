import {Component, OnInit} from '@angular/core';
import {map} from 'rxjs/operators';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {IndicatorsApiService} from '../services/indicators-api.service';
import {ChartRange, IndicatorChartData, IndicatorDetails} from '../models/indicator';
import {BehaviorSubject, Observable} from 'rxjs';
import {FormControl} from '@angular/forms';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {
  ExpandGraphDialogComponent,
  ExpandGraphDialogData
} from './show-dialog/expand-graph-dialog/expand-graph-dialog.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  supportIndicators: IndicatorDetails[] = [];

  showGraphData: GraphData[] = [];

  supportIndicatorsFormControl = new FormControl();

  currentIndicators: IndicatorDetails[] = []

  dataLoading$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  /** Based on the screen size, switch from standard to one column per row */
  cards: Observable<GraphData[]> = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({matches}) => {
      // if (matches) {
      //   return [
      //     { title: 'Card 1', cols: 1, rows: 1 },
      //     { title: 'Card 2', cols: 1, rows: 1 },
      //     { title: 'Card 3', cols: 1, rows: 1 },
      //     { title: 'Card 4', cols: 1, rows: 1 }
      //   ];
      // }
      return this.showGraphData;
    })
  );

  constructor(
    private breakpointObserver: BreakpointObserver,
    private indicatorsApiService: IndicatorsApiService,
    public dialog: MatDialog) {
    indicatorsApiService.getAllIndicators().subscribe(response => {
      this.supportIndicators = response;
      indicatorsApiService.getDefaultIndicators().subscribe(value => {
        let indicatorIdDefaults = this.supportIndicators.filter(value1 => value.includes(value1.name));
        this.supportIndicatorsFormControl.setValue(indicatorIdDefaults);
      });
    });
    this.supportIndicatorsFormControl.valueChanges.subscribe(value => {
      let needAdd: IndicatorDetails[] = value.filter((v: IndicatorDetails) => !this.currentIndicators.includes(v));
      let needRemove: IndicatorDetails[] = this.currentIndicators.filter((v: IndicatorDetails) => !value.includes(v));

      needRemove.forEach(value1 => {
        this.removeIndicator(value1.id);
      })

      if (needAdd.length > 0) {
        this.addIndicator(needAdd.map(value => value.id));
      }

      this.currentIndicators = value;
    });
  }

  ngOnInit(): void {
  }

  addIndicator(indicatorIds: string[]): void {
    this.dataLoading$.next(true);
    this.indicatorsApiService.getIndicatorChartDates(indicatorIds, undefined, ChartRange._1_MONTH).subscribe(response => {
      let graphData: GraphData[] = response.map(value => this.convertIndicatorGraphToDataGraph(value));
      this.showGraphData.push(...graphData);
      this.dataLoading$.next(false);
    });
  }

  expendIndicator(graphData: GraphData): void {
    const dialogConfig = new MatDialogConfig<ExpandGraphDialogData>();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    // dialogConfig.height = '80%';
    dialogConfig.width = '100%';

    dialogConfig.data = {
      id: graphData.id,
      name: graphData.name,
      description: graphData.description,
      data: graphData.data
    };

    this.dialog.open(ExpandGraphDialogComponent, dialogConfig);
  }

  removeIndicator(indicatorId: string): void {
    const indexToDelete: number = this.showGraphData.findIndex(value => value.id === indicatorId);
    if (indexToDelete > -1) {
      this.showGraphData.splice(indexToDelete, 1);
      let findIndex = this.supportIndicatorsFormControl.value.findIndex((value: IndicatorDetails) => value.id === indicatorId);
      if (findIndex > -1) {
        this.supportIndicatorsFormControl.value.splice(findIndex, 1);
        this.supportIndicatorsFormControl.setValue(this.supportIndicatorsFormControl.value);
      }
    }
  }

  private convertIndicatorGraphToDataGraph(indicatorGraph: IndicatorChartData): GraphData {
    const data: [number, (number | null)][] = [];
    if (indicatorGraph && indicatorGraph.data) {
      for (let i = 0; i < indicatorGraph.data.length; i++) {
        let value: number = indicatorGraph.data[i];
        let timestamp: number = indicatorGraph.timestamp[i];
        data.push([timestamp, value])
      }
    }
    return {
      id: indicatorGraph.id,
      name: indicatorGraph.name,
      description: indicatorGraph.description,
      data: data,
      cols: 1,
      rows: 1
    }
  }

  onActiveRangeChange(card: GraphData, activeRange: ChartRange) {
    this.indicatorsApiService.getIndicatorChartDates([card.id], undefined, activeRange).subscribe(response => {
      let graphData: GraphData[] = response.map(value => this.convertIndicatorGraphToDataGraph(value));
      card.data = graphData[0].data;
    });
  }
}

export type GraphData = {
  id: string;
  name: string;
  description: string;
  data: [number, (number | null)][];
  cols?: number;
  rows?: number;
}
