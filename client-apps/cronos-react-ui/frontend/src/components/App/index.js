// Dependencies
import _ from 'lodash';
import React, { Component } from 'react';
// Externals
import Cart from '../Cart';
import ShowProduct from '../ShowProduct';
import Products from '../Products';
import Home from '../Home';
import { Navbar, Footer } from '../Main/components';
import { Route, Switch, withRouter } from 'react-router-dom';
import './index.css';
import 'bootstrap/dist/css/bootstrap.css';

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cart: {
        data: {},
        total: 0
      },
      scrolled: false,
      index: 0,
    };
  }
  componentWillUnmount() {
    window.removeEventListener('scroll');
  }

  componentWillMount() {
    window.addEventListener('scroll', () =>{
      let supportPageOffset = window.pageXOffset !== undefined;
      let isCSS1Compat = ((document.compatMode || '') === 'CSS1Compat');
      const scroll = {
         x: supportPageOffset ? window.pageXOffset : isCSS1Compat ? document.documentElement.scrollLeft : document.body.scrollLeft,
         y: supportPageOffset ? window.pageYOffset : isCSS1Compat ? document.documentElement.scrollTop : document.body.scrollTop
      };
  
      if(scroll.y > 50 && !this.state.scrolled){
        this.setState({
          scrolled: true
        });
      } else if (scroll.y < 50 && this.state.scrolled) {
        this.setState({
          scrolled: false
        });
      }
    });//ms
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
      console.log("Added to Cart "+product.title);
      
      const self = this;
      const url = '/cart/add/?asin='+product.id;
      // let requestData = new FormData();
      // requestData.append( "json", JSON.stringify( {asin: product.id} ));

      fetch(url, {  
        method: 'GET',
        //body: requestData
      })
        .then(data => {
          if(data.ok) {
            self.setState({
              cart: {
                data: {[product.id] : 1},
                total: 1 //self.totalReducer(data)
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

  removeItemFromCart = (product) => {
    if (product) {
      console.log("Removed from Cart "+product.title);
      
      const self = this;
      const url = '/cart/remove/?asin='+product.id;
      //let requestData = new FormData();
      // requestData.append( "json", JSON.stringify( {asin: product.id} ));

      fetch(url, {  
        method: 'GET',
        //body: requestData
      })
        .then(data => {
          if(data.ok) {
            self.setState({
              cart: {
                data: {},
                total: 0 //self.totalReducer(data)
              }
            });
          } else {
            const dataMerged = {};
            const data = self.state.cart.data;
            dataMerged[product.id] = data[product.id] ? data[product.id] + 1 : 1;
            
            self.setState({
              cart: {
                data: {},
                total: 0
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
        <Navbar cart={this.state.cart} scrolled={this.state.scrolled}/>
        <Switch>
          <Route exact path="/" 
            render={(props) => (
              <Home
                addItemToCart={this.addItemToCart} />
            )} />
      
          <Route path="/cart" 
            render={(props) => (
              <Cart
                cart={this.state.cart} removeItemFromCart={this.removeItemFromCart}/>
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
