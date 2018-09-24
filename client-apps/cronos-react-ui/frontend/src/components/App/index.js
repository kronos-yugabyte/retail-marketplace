// Dependencies
import axios from 'axios';
import React, { Component } from 'react';
// Externals
import Cart from '../Cart';
import ShowProduct from '../ShowProduct';
import Products from '../Products';
import { Navbar, Footer } from '../Main/components';
import { Route, Switch } from 'react-router-dom';
import './index.css';

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cart: {
        data: {},
        total: 0
      },
      index: 0,
    };
  }

  totalReducer(data) {
    let sum = 0;
    for( var el in data ) {
      if( data.hasOwnProperty( el ) ) {
        sum += parseFloat( data[el] );
      }
    }
    return sum;
  }

  addItemToCart = (product) => {
    if (product) {
      console.warn(this.state);
      console.log("Added to Cart "+product.title);
      
      const self = this;
      const url = '/shoppingCart/addProduct';
      let requestData = new FormData();
      requestData.append( "json", JSON.stringify( {asin: product.id} ));

      fetch(url, {  
        method: 'POST',
        mode: "no-cors",
        body: requestData
      })
        .then(data => {
          if(data.ok) {
            self.setState({
              cart: {
                data,
                total: self.totalReducer(data)
              }
            });
          } else {
            const dataMerged = {};
            const data = self.state.cart.data;
            dataMerged[product.id] = data[product.id] ? data[product.id] + 1 : 1;
            
            self.setState({
              cart: {
                data: {...data , ...dataMerged},
                total: self.totalReducer({...data , ...dataMerged})
              }
            });
          }
        })
        .catch(error => {
          console.warn('Request failed', error);

        });
    }
  }

  // setSortName(query) {
  //   switch (query) {
  //     case "num_stars":
  //       return "Books with the Highest Rating";
  //     case "num_reviews":
  //       return "Books with the Most Reviews";
  //     case "num_buys":
  //       return "Best Selling Books";
  //     case "num_views":
  //       return "Books with the Most Pageviews";
  //     default:
  //       return "";
  //   }
  // }

  render() {
    return(

      <div>
        <Navbar cart={this.state.cart}/>
        <Switch>
          <Route exact path="/" 
            render={(props) => (
              <Products
                addItemToCart={this.addItemToCart} />
            )} />
      
          <Route path="/cart" 
            render={(props) => (
              <Cart
                cart={this.state.cart} />
            )} />
      
          <Route path="/business"
            render={(props) => (
              <Products
                name={"Business Books"}
                query={"category/business"} />
            )} />
          <Route path="/cookbooks"
            render={(props) => (
              <Products
                name={"Cookbooks"}
                query={"category/cookbooks"} />
            )} />
          <Route path="/mystery"
            render={(props) => (
              <Products
                name={"Mystery & Suspense"}
                query={"category/mystery"} />
            )} />
          <Route path="/scifi"
            render={(props) => (
              <Products
                name={"Sci-Fi & Fantasy"}
                query={"category/scifi"} />
            )} />
      
          <Route path="/sort/:query"
            render={(props) => (
              <Products
                sort={props.match.params.query}
                addItemToCart={this.addItemToCart} />
            )} />
      
          <Route exact path="/item/:id" render={(props) => (
            <ShowProduct {...props} addItemToCart={this.addItemToCart}/>
          )}/>
        </Switch>
        <Footer />
      </div>
    )
  }
}
