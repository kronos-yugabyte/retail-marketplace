// Dependencies
import axios from 'axios';
import React, { Component } from 'react';
//Internals
import Highlights from './components/Highlights';
import Products from '../Products';
import './index.css';

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cart: {},
    };
  }

  render() {
    return (
      <div>
        <div className="content">
       	  <Highlights />
        </div>
        {this.props.children}
        <div className="content">
          <Products addItemToCart={this.props.addItemToCart}/>
        </div>
      </div>
    );
  }
}

export default Home;
