import {ChartType} from '../../core/models/dashboard.model';

const revenueChart: ChartType = {
  series: [{
    name: 'chiffre',
    type: 'column',
    data: []
  }, {
    name: 'input',
    type: 'line',
    data: []
  }, {
    name: 'output',
    type: 'line',
    data: []
  }],
  chart: {
    height: 280,
    type: 'line',
    toolbar: {
      show: false,
    }
  },
  stroke: {
    width: [0, 3],
    curve: 'smooth'
  },
  plotOptions: {
    bar: {
      horizontal: false,
      columnWidth: '20%',
    },
  },
  dataLabels: {
    enabled: true,
  },
  legend: {
    show: false,
  },
  colors: ['#5664d2', '#1cbb8c', '#ea1854'],
};


const salesAnalytics: ChartType = {
  series: [42, 26, 15],
  chart: {
    height: 230,
    type: 'donut',
  },
  labels: ['Product A', 'Product B', 'Product C'],
  plotOptions: {
    pie: {
      donut: {
        size: '75%'
      }
    }
  },
  dataLabels: {
    enabled: false
  },
  legend: {
    show: false,
  },
  colors: ['#5664d2', '#1cbb8c', '#eeb902'],
};

const sparklineEarning: ChartType = {
  series: [72],
  chart: {
    type: 'radialBar',
    wight: 60,
    height: 60,
    sparkline: {
      enabled: true
    }
  },
  dataLabels: {
    enabled: false
  },
  colors: ['#5664d2'],
  stroke: {
    lineCap: 'round'
  },
  plotOptions: {
    radialBar: {
      hollow: {
        margin: 0,
        size: '70%'
      },
      track: {
        margin: 0,
      },

      dataLabels: {
        show: false
      }
    }
  }
};

const sparklineMonthly: ChartType = {
  series: [65],
  chart: {
    type: 'radialBar',
    wight: 60,
    height: 60,
    sparkline: {
      enabled: true
    }
  },
  dataLabels: {
    enabled: false
  },
  colors: ['#1cbb8c'],
  stroke: {
    lineCap: 'round'
  },
  plotOptions: {
    radialBar: {
      hollow: {
        margin: 0,
        size: '70%'
      },
      track: {
        margin: 0,
      },

      dataLabels: {
        show: false
      }
    }
  }
};


const transactions = [
  {
    orderid: '#NZ1563',
    date: '28 Mar, 2020',
    billingname: 'Frank Dean',
    total: '$164',
    paymentstatus: 'Unpaid'
  },
  {
    orderid: '#NZ1564',
    date: '28 Mar, 2020',
    billingname: 'Eddy Torres',
    total: '$141',
    paymentstatus: 'Paid'
  },
  {
    orderid: '#NZ1565',
    date: '29 Mar, 2020',
    billingname: 'Jamison Clark',
    total: '$123',
    paymentstatus: 'Paid'
  },
  {
    orderid: '#NZ1566',
    date: '30 Mar, 2020',
    billingname: 'Jewel Buckley',
    total: '$112',
    paymentstatus: 'Paid'
  },
  {
    orderid: '#NZ1567',
    date: '31 Mar, 2020',
    billingname: 'Jeffrey Waltz',
    total: '$105',
    paymentstatus: 'Unpaid'
  },
  {
    orderid: '#NZ1568',
    date: '01 Apr, 2020',
    billingname: 'Jefferson Allen',
    total: '$160',
    paymentstatus: 'Chargeback'
  }
];

const statData = [
  {
    icon: 'ri-stack-line',
    title: 'Number of Sales',
    value: '1452'
  }, {
    icon: 'ri-store-2-line',
    title: 'Sales Revenue',
    value: '$ 38452'
  }, {
    icon: 'ri-briefcase-4-line',
    title: 'Average Price',
    value: '$ 15.4'
  }
];

export {revenueChart, salesAnalytics, sparklineEarning, sparklineMonthly, transactions, statData};
