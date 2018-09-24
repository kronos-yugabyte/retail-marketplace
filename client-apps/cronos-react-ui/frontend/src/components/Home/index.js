// Dependencies
import axios from 'axios';
import React, { Component } from 'react';
//Internals
import Highlights from '../Main/components/Highlights';
import Hero from '../Main/components/Hero';
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
      </div>
    );
  }
}

export default Home;
