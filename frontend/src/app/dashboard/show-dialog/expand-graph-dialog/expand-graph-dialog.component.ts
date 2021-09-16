import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {ChartRange, IndicatorChartData} from '../../../models/indicator';
import {IndicatorsApiService} from '../../../services/indicators-api.service';

@Component({
  selector: 'app-expand-graph-dialog',
  templateUrl: './expand-graph-dialog.component.html',
  styleUrls: ['./expand-graph-dialog.component.scss']
})
export class ExpandGraphDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public graphData: ExpandGraphDialogData,
              private indicatorsApiService: IndicatorsApiService) {
  }

  ngOnInit(): void {
  }

  onActiveRangeChange(activeRange: ChartRange) {
    this.indicatorsApiService.getIndicatorChartDates([this.graphData.id], undefined, activeRange).subscribe(response => {
      let graphData: ExpandGraphDialogData[] = response.map(value => this.convertIndicatorGraphToDataGraph(value));
      this.graphData.data = graphData[0].data;
    });
  }

  private convertIndicatorGraphToDataGraph(indicatorGraph: IndicatorChartData): ExpandGraphDialogData {
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
      data: data
    }
  }

}

export declare class ExpandGraphDialogData {
  id: string;
  name: string;
  description: string;
  data: [number, (number | null)][];
}
