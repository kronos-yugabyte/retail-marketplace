//Dependencies
import React, { Component } from 'react';
import { Icon } from 'react-materialize';
import { Link } from 'react-router-dom';
import Highlights from '../Main/components/Highlights';
//Internals
import './index.css';

class Products extends Component {
  state = {current_query: "", products: []}

  componentDidMount() {
    this.fetchProducts();
  }

  fetchProducts() {
    if (this.state.products.length === 0 ||
        this.state.current_query !== this.props.query) {

      this.setState({ current_query: this.props.query });
      //this.state.current_query = this.props.query;
      var url = '/products';
      if (this.props.query) {
        url = '/products/' + this.props.query;
      }
      fetch(url)
        .then(res => res.json())
        .then(products => this.setState({ products }));
    }
  }

  setSortName(query) {
    switch (query) {
      case "num_stars":
        return "Books with the Highest Rating";
      case "num_reviews":
        return "Books with the Most Reviews";
      case "num_buys":
        return "Best Selling Books";
      case "num_views":
        return "Books with the Most Pageviews";
      default:
        return "";
    }
  }

  render() {
    let stars = ["star_border", "star_border", "star_border", "star_border", "star_border"];
    const self = this;
    debugger;
    return (
      <div className="content">
        <div className="products">
        <div className="products-title">
          <h1 id="highlights-title">{this.props.name || "BOOKS AT YUGASTORE"}</h1>
        </div>

          <div className="items">
            {this.state.products.sort((a, b)=>{
              if(self.props.sort) {
                return a[self.props.sort] > b[self.props.sort];
              }
              return false;
            }).map((product) => {
                if (product.avg_stars > 0) {
                  stars[0] = (product.avg_stars < 1) ? "star_half" : "star";
                }
                if (product.avg_stars > 1) {
                  stars[1] = (product.avg_stars < 2) ? "star_half" : "star";
                }
                if (product.avg_stars > 2) {
                  stars[2] = (product.avg_stars < 3) ? "star_half" : "star";
                }
                if (product.avg_stars > 3) {
                  stars[3] = (product.avg_stars < 4) ? "star_half" : "star";
                }
                if (product.avg_stars > 4) {
                  stars[4] = (product.avg_stars < 5) ? "star_half" : "star";
                }
                //debugger;
                return (
                  <div className="item" key={product.id}>
                    <Link to={`/item/${product.id}`}>
                    <div className="product-img">
                      <img alt={product.title} src={product.imUrl} />
                    </div>
                    <div className="product-details">
                      <h1 id="product-name">{product.title}</h1>
                    </div>
                    </Link>
                    <div className="reviews-add">
                      <div className="stars">
                        <Icon small id="add-icon" className="review-star">{ stars[0] }</Icon>
                        <Icon small id="add-icon" className="review-star">{ stars[1] }</Icon>
                        <Icon small id="add-icon" className="review-star">{ stars[2] }</Icon>
                        <Icon small id="add-icon" className="review-star">{ stars[3] }</Icon>
                        <Icon small id="add-icon" className="review-star">{ stars[4] }</Icon>
                      </div>
                      {product.num_stars} stars from {product.num_reviews} reviews
                    </div>
                    <button onClick={() => this.props.addItemToCart(product)} className="price-add">
                      <h5 id="product-price">${product.price}</h5>
                      <Icon small id="add-icon">add_shopping_cart</Icon>
                    </button>
                  </div>
                )
            })}
          </div>
        </div>
      </div>
    );
  }
}

export default Products;
