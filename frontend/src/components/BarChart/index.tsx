
import axios from 'axios';
import { useEffect, useState } from 'react';
import Chart from 'react-apexcharts';
import { round } from 'utils/format';
import { SaleSuccess } from 'types/sale';
import { BASE_URL } from 'utils/request';

type SeriesData = {
    name: string;
    data: number[];
}

type ChartData = {
    labels: {
        categories: string[];
    };
    series: SeriesData[];
}

const BarChart = () => {


    const [chatData, setChartData] = useState<ChartData>({
        labels: {
            categories: []
        },
        series: [
            {
                name: "",
                data: []                   
            }
        ]
    });

    useEffect(()=> {

        axios.get(`${BASE_URL}/sales/success-by-seller`)
        .then(repoonse => {
            const data = repoonse.data as SaleSuccess[];
            const myLabels = data.map(x => x.sallerName);
            const mySeries = data.map(x => round(100.0* x.deals / x.visited, 1));
            setChartData({
                labels: {
                    categories: myLabels
                },
                series: [
                    {
                        name: "% Sucesso",
                        data: mySeries                   
                    }
                ]
            });
        });

    }, []); 

    const options = {
        plotOptions: {
            bar: {
                horizontal: true,
            }
        },
    };
    
   
    
    return (
       <Chart 
            options={{...options, xaxis: chatData.labels}}
            series={chatData.series}
            type="bar"
            height="240"
       />
    );
}

export default BarChart;
