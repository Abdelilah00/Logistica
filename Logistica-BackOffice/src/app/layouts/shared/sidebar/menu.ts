import {MenuItem} from './menu.model';

export const MENU: MenuItem[] = [
  {
    label: 'MENUITEMS.MENU.TEXT',
    isTitle: true
  },
  {
    label: 'MENUITEMS.DASHBOARDS.TEXT',
    icon: 'ri-dashboard-line',
    badge: {
      variant: 'success',
      text: 'MENUITEMS.DASHBOARDS.BADGE',
    },
    subItems: [
      {
        label: 'MENUITEMS.DASHBOARDS.LIST.ANALYTICS',
        icon: 'ri-product-hunt-line',
        link: '/dashboards/analytics'
      },
      {
        label: 'MENUITEMS.DASHBOARDS.LIST.ALERTS',
        icon: 'ri-product-hunt-line',
        link: '/dashboards/alerts'
      },
      {
        label: 'MENUITEMS.DASHBOARDS.LIST.PREDICTIONS',
        icon: 'ri-product-hunt-line',
        link: '/dashboards/predictions'
      }
    ]
  },
  {
    label: 'MENUITEMS.ACTORS.TEXT',
    icon: 'ri-product-hunt-line',
    subItems: [
      {
        label: 'MENUITEMS.ACTORS.LIST.CLIENTS',
        icon: 'ri-product-hunt-line',
        link: '/actors/clients'
      },
      {
        label: 'MENUITEMS.ACTORS.LIST.SUPPLIERS',
        icon: 'ri-product-hunt-line',
        link: '/actors/suppliers'
      },
      {
        label: 'MENUITEMS.ACTORS.LIST.RESPONSIBLE',
        icon: 'ri-product-hunt-line',
        link: '/actors/responsible'
      }
    ]
  },
  {
    label: 'MENUITEMS.STOCKS.TEXT',
    icon: 'ri-product-hunt-line',
    subItems: [
      {
        label: 'MENUITEMS.STOCKS.LIST.CATEGORY',
        icon: 'ri-product-hunt-line',
        link: '/stocks/category'
      }, {
        label: 'MENUITEMS.STOCKS.LIST.PRODUCT',
        icon: 'ri-product-hunt-line',
        link: '/stocks/product'
      }, {
        label: 'MENUITEMS.STOCKS.LIST.STOCK',
        icon: 'ri-product-hunt-line',
        link: '/stocks/stock'
      }]
  },
  {
    label: 'MENUITEMS.TRANSACTION.TEXT',
    icon: 'ri-file-list-3-line',
    subItems: [
      {
        label: 'MENUITEMS.TRANSACTION.LIST.INPUT',
        link: '/transactions/input',
      }, {
        label: 'MENUITEMS.TRANSACTION.LIST.OUTPUT',
        link: '/transactions/output',
      }, {
        label: 'MENUITEMS.TRANSACTION.LIST.TRANSFER',
        link: '/transactions/transfer',
      }
    ]
  },
];
