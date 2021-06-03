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
    link: '/'
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
