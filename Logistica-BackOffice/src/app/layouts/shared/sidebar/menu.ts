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
        link: '/',
        parentId: 5
      }, {
        id: 7,
        label: 'MENUITEMS.TRANSACTION.LIST.OUTPUT',
        link: '/',
        parentId: 5
      }]
  },
  {
    id: 3,
    label: 'MENUITEMS.PRODUCTS.TEXT',
    icon: 'ri-product-hunt-line',
    subItems: [
      {
        id: 4,
        label: 'MENUITEMS.PRODUCTS.LIST.PRODUCT',
        link: '/',
        parentId: 3
      }]
  },
  {
    id: 8,
    label: 'MENUITEMS.STOCKS.TEXT',
    icon: 'ri-product-hunt-line',
    link: '/'
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
        link: '/pages/login-1',
        parentId: 18
      },
      {
        id: 20,
        label: 'MENUITEMS.AUTHENTICATION.LIST.REGISTER',
        link: '/pages/register-1',
        parentId: 18
      },
      {
        id: 21,
        label: 'MENUITEMS.AUTHENTICATION.LIST.RECOVERPWD',
        link: '/pages/recoverpwd-1',
        parentId: 18
      },
      {
        id: 22,
        label: 'MENUITEMS.AUTHENTICATION.LIST.LOCKSCREEN',
        link: '/pages/lock-screen-1',
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
