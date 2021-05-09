import {MenuItem} from './menu.model';

export const MENU: MenuItem[] = [
  {
    id: 1,
    label: 'MENUITEMS.MENU.TEXT',
    isTitle: true
  },
  {
    id: 2,
    label: 'MENUITEMS.DASHBOARDS.TEXT',
    icon: 'ri-dashboard-line',
    badge: {
      variant: 'success',
      text: 'MENUITEMS.DASHBOARDS.BADGE',
    },
    link: '/'
  },
  {
    id: 5,
    label: 'MENUITEMS.TRANSACTION.TEXT',
    icon: 'ri-file-list-3-line',
    subItems: [
      {
        id: 6,
        label: 'MENUITEMS.TRANSACTION.LIST.INPUT',
        link: '/transactions/inputs',
        parentId: 5
      }, {
        id: 7,
        label: 'MENUITEMS.TRANSACTION.LIST.OUTPUT',
        link: '/transactions/outputs',
        parentId: 5
      }]
  },
  {
    id: 3,
    label: 'MENUITEMS.PRODUCTS.TEXT',
    icon: 'ri-product-hunt-line',
    link: '/products'
  },
  {
    label: 'MENUITEMS.STOCKS.TEXT',
    icon: 'ri-product-hunt-line',
    subItems: [
      {
        label: 'MENUITEMS.STOCKS.LIST.STOCK',
        icon: 'ri-product-hunt-line',
        link: '/stocks/stock'
      },
      {
        label: 'MENUITEMS.STOCKS.LIST.RESPO',
        icon: 'ri-product-hunt-line',
        link: '/stocks/stock-respo'
      }]
  },
  {
    label: 'MENUITEMS.TASKS.TEXT',
    icon: 'ri-product-hunt-line',
    link: '/tasks'
  },
  {
    label: 'MENUITEMS.AUDITS.TEXT',
    icon: 'ri-product-hunt-line',
    link: '/audits'
  },

  {
    id: 9,
    label: 'MENUITEMS.SUPPLIERS.TEXT',
    icon: 'ri-product-hunt-line',
    link: '/suppliers'
  },
  {
    id: 17,
    label: 'MENUITEMS.PAGES.TEXT',
    isTitle: true
  },
  {
    id: 18,
    label: 'MENUITEMS.AUTHENTICATION.TEXT',
    icon: 'ri-account-circle-line',
    subItems: [
      {
        id: 19,
        label: 'MENUITEMS.AUTHENTICATION.LIST.LOGIN',
        link: '/extrapages/login-1',
        parentId: 18
      },
      {
        id: 20,
        label: 'MENUITEMS.AUTHENTICATION.LIST.REGISTER',
        link: '/extrapages/register-1',
        parentId: 18
      },
      {
        id: 21,
        label: 'MENUITEMS.AUTHENTICATION.LIST.RECOVERPWD',
        link: '/extrapages/recoverpwd-1',
        parentId: 18
      },
      {
        id: 22,
        label: 'MENUITEMS.AUTHENTICATION.LIST.LOCKSCREEN',
        link: '/extrapages/lock-screen-1',
        parentId: 18
      }
    ]
  },
  {
    id: 23,
    label: 'MENUITEMS.PARAMS.TEXT',
    icon: 'ri-account-circle-line',
    subItems: [
      {
        id: 24,
        label: 'MENUITEMS.PARAMS.LIST.PRODUCT',
        link: '/',
        parentId: 23
      },
      {
        id: 25,
        label: 'MENUITEMS.PARAMS.LIST.PRODUCT',
        link: '/',
        parentId: 23
      },
      {
        id: 26,
        label: 'MENUITEMS.PARAMS.LIST.PRODUCT',
        link: '/',
        parentId: 23
      },
      {
        id: 27,
        label: 'MENUITEMS.PARAMS.LIST.PRODUCT',
        link: '/',
        parentId: 23
      }
    ]
  },
];
