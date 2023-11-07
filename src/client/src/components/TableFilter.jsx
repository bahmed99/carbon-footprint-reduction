import React, { useState } from 'react'
import { Table } from 'antd';
import BrandsTab from './BrandsTab';
import { Pagination } from 'antd';

export default function TableFilter(props) {

  const [loading, setLoading] = useState(props.loading)

  const tableProps = {
    loading,
  };

  const HandleTableChange = (pagination, size) => {
    console.log(props.filters)
    props.setPageSize(size)
    props.setPageNumber(pagination)
  };

  return (
    <div className='ContainerTable'>
      <BrandsTab setPageNumber={props.setPageNumber} setPageSize={props.setPageSize} pageNumber={props.pageNumber} pageSize={props.pageSize} countData={props.countData} data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Brands"} url={"brands"} filters={props.filters} setFilters={props.setFilters} filtre={"brandIds"} /> 
      <Table pagination={false} columns={props.columns} dataSource={props.data} {...tableProps} size='small' bordered />
      <div className='PaginationTable'>
        <Pagination showSizeChanger defaultCurrent={1} total={props.countData} onChange={HandleTableChange} />
      </div>

    </div>
  )
}
