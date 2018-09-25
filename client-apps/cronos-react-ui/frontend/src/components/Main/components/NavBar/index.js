// Dependencies
import React, { Component } from 'react';
import { Logo } from '../';
import { Icon } from "../../../common";
import { NavLink, withRouter } from 'react-router-dom';
// Internals
import './index.css';

class Navbar extends Component {
  state = {itemsInCart: 0}

  componentDidMount() {
    // TODO: add this
    //fetch('http://localhost:3001/cart/itemsInCart')
    //  .then(res => res.json())
    //  .then(itemsInCart => this.setState({ itemsInCart }));
  }

  constructor(props) {
    super(props);
    this.state = {
      itemsInCart: 0,
    };
  }

  render() {
    const { match, location, history } = this.props
    const notIndex = location.pathname!=="/";
    return(
    <nav className={`nav-bar ${this.props.scrolled || notIndex ? 'nav-bar-scrolled' : '' }`}>
      <NavLink to="/">
        <Logo mode={this.props.scrolled || notIndex ? 'dark' : 'light'} />
      </NavLink>
      <div className="nav-links">
        <ul>
          <li><NavLink activeClassName="selected" className="nav-link" to="/business">
            <span className="nav-link-icon"><Icon icon="book" /></span>
            <span className="nav-link-text">Books</span>
          </NavLink></li>
          <li><NavLink activeClassName="selected" className="nav-link" to="/mystery">
            <span className="nav-link-icon"><Icon icon="cart" /></span>
            <span className="nav-link-text">Music</span>
          </NavLink></li>
          <li><NavLink activeClassName="selected" className="nav-link" to="/cookbooks">
            <span className="nav-link-icon"><Icon icon="art" /></span>
            <span className="nav-link-text">Beauty</span>
          </NavLink></li>
          <li><NavLink activeClassName="selected" className="nav-link" to="/scifi">
            <span className="nav-link-icon"><Icon icon="scifi" /></span>
            <span className="nav-link-text">Electronics</span>
          </NavLink></li>
        </ul>
      </div>
      <div className='nav-cart'>
        <NavLink className={`${this.props.cart.total ? 'nav-cart-active' : '' }`} to="/cart">
          <Icon icon="cart" color={this.props.scrolled || notIndex ? '#000000' : '#ffffff' }/>Cart
          {this.props.cart.total > 0 && <span className="nav-cart-count">{this.props.cart.total}</span>}
        </NavLink>
      </div>
    </nav>
    )
  }
}

export default withRouter(Navbar);
