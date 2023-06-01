import React from 'react';
import { order } from "../../model/orderType";
import Order from './Order';

function Orders(props: { orders: order[], setOrders: (value: (((prevState: order[]) => order[]) | order[])) => void }) {
    return (
        <div className="orders-container">

            {props.orders?.map((order) => {
                return (
                    <Order
                        key={order.id}
                        id={order.id}
                        dateTimeOfOrder={order.dateTimeOfOrder}
                        orderedProducts={order.orderedProducts}
                        totalCost={order.totalCost}
                        completed={order.completed} setOrders={props.setOrders}
                    />)
            })}
        </div>
    );
}

export default Orders;