//Dependencies
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import map from 'lodash/map';
//Internals
import './index.css';

class CartProducts extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return(
      <div>
        <h1>This is the cart {this.props.cart.total}</h1>
        <div className="items">
          {map(this.props.cart.date, (product) => {
            <h1>{product.name}</h1>
          })}
        </div>
      </div>
    );
  }
}

export default CartProducts;
