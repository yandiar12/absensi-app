import React from 'react'
import CIcon from '@coreui/icons-react'
import {
  cilBook,
  cilDescription,
  cilHome,
  cilNotes,
} from '@coreui/icons'
import { CNavGroup, CNavItem, CNavTitle } from '@coreui/react'

const _nav = [
  {
    component: CNavItem,
    name: 'Absensi App',
    to: '/',
    icon: <CIcon icon={cilHome} customClassName="nav-icon" />
  },
  {
    component: CNavTitle,
    name: 'Menu',
  },
  {
    component: CNavGroup,
    name: 'Form Pengajuan',
    to: '/pengajuan',
    icon: <CIcon icon={cilNotes} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'Cuti',
        to: '/buttons/buttons',
      },
      {
        component: CNavItem,
        name: 'Ketidakhadiran',
        to: '/buttons/button-groups',
      },
      {
        component: CNavItem,
        name: 'Perjalanan Dinas',
        to: '/buttons/dropdowns',
      },
    ],
  },
  {
    component: CNavGroup,
    name: 'Data Pengajuan',
    icon: <CIcon icon={cilDescription} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: 'Data Cuti',
        to: '/forms/form-control',
      },
      {
        component: CNavItem,
        name: 'Data Ketidakhadiran',
        to: '/forms/select',
      },
      {
        component: CNavItem,
        name: 'Data Perjalanan Dinas',
        to: '/forms/checks-radios',
      }
    ],
  },
  {
    component: CNavItem,
    name: 'Laporan Absensi',
    to: '/charts',
    icon: <CIcon icon={cilBook} customClassName="nav-icon" />,
  }
]

export default _nav
