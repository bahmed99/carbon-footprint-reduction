import React, { useState, useEffect } from "react";
import Axios from "axios";
import { Table, Tabs } from 'antd';
import Navbar from "../components/Navbar";
export default function StatesPage() {
    const { TabPane } = Tabs;

    const [rentals, setRentals] = useState([]);
    const [sales, setSales] = useState([]);
    const [reparations, setReparations] = useState([]);
    const [activeTab, setActiveTab] = useState('sales');
    const [rentalsCount, setRentalsCount] = useState(0);
    const [salesCount, setSalesCount] = useState(0);
    const [reparationsCount, setReparationsCount] = useState(0)
    const [salesPagination, setSalesPagination] = useState({ current: 0, pageSize: 10, total: 0 });
    const [rentalsPagination, setRentalsPagination] = useState({ current: 0, pageSize: 10, total: 0 });
    const [reparationsPagination, setReparationsPagination] = useState({ current: 0, pageSize: 10, total: 0 });
    const Authorization = 'Bearer' + localStorage.getItem('accessToken');
    // Colonnes pour l'état de location

    const [rentalscolumns, setRentalsColumns] = useState([
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'id',
        },
        {
            title: 'Brand Name',
            dataIndex: ['rentedVehicle', 'model', 'brand', 'name'],
            key: 'brandName',
        },
        {
            title: 'Model Name',
            dataIndex: ['rentedVehicle', 'model', 'name'],
            key: 'modelName',
        },
        {
            title: 'Rental Start Date',
            dataIndex: 'rentalStartDate',
            key: 'rentalStartDate',
        },
        {
            title: 'Rental End Date',
            dataIndex: 'rentalEndDate',
            key: 'rentalEndDate',
        },
        {
            title: 'Rental Fee (€)',
            dataIndex: 'rentalFee',
            key: 'rentalFee',

        }


    ])

    // Colonnes pour l'état de vente
    const salesColumns = [
        {   title: 'ID',
            dataIndex: 'id',
            key: 'id',
        },
        {
            title: 'Brand Name',
            dataIndex: ['vehicle', 'model', 'brand', 'name'],
            key: 'brandName',
        },
        {
            title: 'Model Name',
            dataIndex: ['vehicle', 'model', 'name'],
            key: 'modelName',
        },
        {
            title: 'Sale Date',
            dataIndex: 'dateSale',
            key: 'dateSale',
        },
        {
            title: 'Delivery Date',
            dataIndex: 'dateDilevery',
            key: 'dateDilevery',
        },

        {
            title: 'Sale Price (€)',
            dataIndex: 'price',
            key: 'price',
        },
    ];

    // Colonnes pour l'état de réparation
    const reparationsColumns = [
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'id',
        },
        {
            title: 'Brand Name',
            dataIndex: ['repairedVehicle', 'model', 'brand', 'name'],
            key: 'brandName',
        },
        {
            title: 'Model Name',
            dataIndex: ['repairedVehicle', 'model', 'name'],
            key: 'modelName',
        },
        {
            title: 'Repair Start Date',
            dataIndex: 'repairStartDate',
            key: 'repairStartDate',
        },
        {
            title: 'Repair End Date',
            dataIndex: 'repairEndDate',
            key: 'repairEndDate',
        },
        {
            title: 'Repair Cost (€)',
            dataIndex: 'repairCost',
            key: 'repairCost',
        },
    ];

    useEffect(() => {
        const fetchTablesCount = async () => {
            try {
                const [reparationsCountResponse, rentalsCountResponse, salesCountResponse] = await Promise.all([
                    Axios.get(process.env.REACT_APP_API_URL + 'reparations/size', {
                        headers: {
                            Authorization: Authorization
                        },
                    }),
                    Axios.get(process.env.REACT_APP_API_URL + 'rentals/size', {
                        headers: {
                            Authorization: Authorization
                        },
                    }),
                    Axios.get(process.env.REACT_APP_API_URL + 'sales/size', {
                        headers: {
                            Authorization: Authorization
                        },
                    }),
                ]);

                setReparationsCount(reparationsCountResponse.data);
                setReparationsPagination({ ...reparationsPagination, total: reparationsCountResponse.data });
                setRentalsCount(rentalsCountResponse.data);
                setRentalsPagination({ ...rentalsPagination, total: rentalsCountResponse.data });
                setSalesCount(salesCountResponse.data);
                setSalesPagination({ ...salesPagination, total: salesCountResponse.data });

                fetchSalesData(0, salesPagination.pageSize);
                
            } catch (error) {
                console.log(error);
            }
        };

         fetchTablesCount();

    }, []);



    const handleTabChange = (key) => {
        setActiveTab(key);
        if (key === 'sales' && sales.length === 0) {
            fetchSalesData(0, salesPagination.pageSize);
        } else if (key === 'reparations' && reparations.length === 0) {
            fetchReparationsData(0, reparationsPagination.pageSize);
        } else if (key === 'rentals' && rentals.length === 0) {
            fetchRentalsData(0, rentalsPagination.pageSize);
        }
    };

    const handleSalesTableChange = (pagination) => {
        setSalesPagination(pagination);
        fetchSalesData(pagination.current - 1, pagination.pageSize);
    };

    const handleReparationsTableChange = (pagination) => {
        setReparationsPagination(pagination);
        fetchReparationsData(pagination.current - 1, pagination.pageSize);
    };


    const handleRentalsTableChange = (pagination) => {
        setRentalsPagination(pagination);
        fetchRentalsData(pagination.current - 1, pagination.pageSize);
    };

    const fetchRentalsData = async (page, size) => {
        try {
            const rentalsResponse = await Axios.get(
                `${process.env.REACT_APP_API_URL}rentals/page/${page}/size/${size}`,
                {
                    headers: {
                        Authorization: Authorization,
                    },
                }
            );

            console.log("rentalsResponse", rentalsResponse);
            setRentals(rentalsResponse.data);
        } catch (error) {
            console.log(error);
        }
    };

    const fetchSalesData = async (page, size) => {
        try {
            const salesResponse = await Axios.get(
                `${process.env.REACT_APP_API_URL}sales/page/${page}/size/${size}`,
                {
                    headers: {
                        Authorization: Authorization,
                    },
                }
            );

            console.log("salesResponse", salesResponse);
            setSales(salesResponse.data);
        } catch (error) {
            console.log(error);
        }
    };

    const fetchReparationsData = async (page, size) => {
        try {
            const reparationsResponse = await Axios.get(
                `${process.env.REACT_APP_API_URL}reparations/page/${page}/size/${size}`,
                {
                    headers: {
                        Authorization: Authorization,
                    },
                }
            );

            console.log("reparationsResponse", reparationsResponse);
            setReparations(reparationsResponse.data);
        } catch (error) {
            console.log(error);
        }
    };




    return (
        <div>
            <div className="statesPage-container">
            <h1 className="statesPage-title">Vehicle States Overview: Explore rental, sales, and repair information for our vehicles.</h1>
            <Tabs activeKey={activeTab} onChange={handleTabChange} className="StatesTable-Tabs">
            <TabPane tab={<span className="custom-tab-label">Sales</span>} key="sales" className="statetabPane">
                    <h2 className="statetab-title">Sales</h2>
                    <Table
                        pagination={{
                            ...salesPagination,
                            total: salesCount,
                            showSizeChanger: false,
                            position: ['bottomCenter']
                        }} 
                        columns={salesColumns}
                        dataSource={sales}
                        size='small'
                        bordered
                        onChange={handleSalesTableChange}
                    />       
                 </TabPane>

                <TabPane tab={<span className="custom-tab-label">Rentals</span>} key="rentals" className="statetabPane">
                    <h2  className="statetab-title" >Rentals</h2>
                    <Table
                         pagination={{
                            ...rentalsPagination,
                            total: rentalsCount,
                            showSizeChanger: false, 
                            position: ['bottomCenter']
                        }} 
                        columns={rentalscolumns}
                        dataSource={rentals}
                        size='small'
                        bordered
                        onChange={handleRentalsTableChange}
                    />       
                </TabPane>

                <TabPane tab={<span className="custom-tab-label">Reparations</span>} key="reparations" className="statetabPane">
                    <h2  className="statetab-title">Reparations</h2>
                    <Table
                        pagination={{
                            ...reparationsPagination,
                            total: reparationsCount,
                            showSizeChanger: false,
                            position: ['bottomCenter']

                        }}
                        columns={reparationsColumns}
                        dataSource={reparations}
                        size='small'
                        bordered
                        onChange={handleReparationsTableChange}

                    />
                </TabPane>
            </Tabs>
            </div>           
        </div>
    );
}

