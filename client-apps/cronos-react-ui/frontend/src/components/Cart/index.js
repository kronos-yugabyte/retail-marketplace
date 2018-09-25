//Dependencies
import React, { Component } from 'react';
import { Button } from '../../components/common';
import PropTypes from 'prop-types';
import map from 'lodash/map';
//Internals
import './index.css';

class CartProducts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      product: {}
    };
  }

  componentDidMount() {
    this.fetchProductDetails(Object.keys(this.props.cart.data)[0]);
  }

  submitCheckout() {
    if (this.state.products.length === 0 ||
        this.state.current_query !== this.props.query) {

      this.setState({ current_query: this.props.query });
      //this.state.current_query = this.props.query;
      var url = '/cart';
      if (this.props.query) {
        url = '/products/' + this.props.query;
      }
      fetch(url)
        .then(res => res.json())
        .then(products => this.setState({ products }));
    }
  }

  fetchCart() {
    //if (this.state.cart) {
      var url = '/cart/get';
      fetch(url)
        .then(res => {res.json()})
        .then(cart => this.setState({ cart }))
    //}
  }

  fetchProductDetails(product_id) {
      this.state.product_id = "" + product_id;
      var url = '/products/details?asin=' + product_id;
      console.log("Fetching url: " + url);
      fetch(url)
        .then(res => res.json())
        .then(product => this.setState({ product }));
  }
  render() {
    console.warn(this.props.cart);
    return(
      <div className="cart-container">
      <div className="container">
        <h5>Items in cart</h5>
        { this.state.product && !!this.props.cart.total && <div className="items">
          <div className="cart-item">
            <div className="product-image">
              <img src={this.state.product.imUrl} alt="product" />
            </div>
            <div className="details">
              {this.state.product.title}
            </div>

            <div className="pricing">
              <h6>{this.state.product.price}</h6> x 1
            </div>
            <div className="actions">
              <Button onClick={() => this.props.removeItemFromCart(this.state.product.id)}  size="meduim">Remove</Button>
            </div>
            {/* {map(this.props.cart.date, (product) => {
              <h1>{product.name}</h1>
            })} */}
          </div>
        </div>}
        { !this.props.cart.total &&
          <h6>Cart is empty</h6>
        }
        { this.state.product && !!this.props.cart.total && <div className="total">
            <div className="details">
            </div>

            <div className="pricing">
              Total:<br/>
              Taxes:
            </div>
            <div className="pricing">
              <h6>{(this.state.product.price*1.1025).toFixed(2)}</h6>
              <h6>{(this.state.product.price*0.1025).toFixed(2)}</h6>
            </div>
            <div className="actions">&nbsp;
            </div>
        </div>}
      </div>
      </div>
    );
  }
}

export default CartProducts;
