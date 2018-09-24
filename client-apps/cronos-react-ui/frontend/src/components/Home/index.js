// Dependencies
import axios from 'axios';
import React, { Component } from 'react';
//Internals
import { Hero, Subscribe } from '../Main/components';
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
        <Hero/>
        <Products addItemToCart={this.props.addItemToCart}/>
        <Subscribe/>
      </div>
    );
  }
}

export default Home;
