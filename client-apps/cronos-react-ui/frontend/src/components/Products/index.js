//Dependencies
import React, { Component } from 'react';
import { Icon } from 'react-materialize';
import { Link } from 'react-router-dom';
import { Row, Col } from 'react-bootstrap';
import Highlights from '../Main/components/Highlights';
//Internals
import './index.css';

class Products extends Component {
  state = {current_query: "", category: null, products: []}

  componentDidMount() {
    this.setState({ category: this.props.category || null }, this.fetchProducts(this.props.category));
  }

  componentWillReceiveProps(nextProps) {
    if (this.state.category !== nextProps.category) this.setState({ category: nextProps.category, products: [] }, this.fetchProducts(nextProps.category));
  }

  fetchProducts(nextCategory) {
    let url, query = '';
    if (nextCategory) {
      url = '/products/category?';
      query = 'name=' + nextCategory + '&';
    }
    else {
      url = '/products?';
    }
    query += "limit=" + (this.props.limit || 10) + '&';
    query += "offset=" + (this.props.offset || 0);
    this.setState({ current_query: url+query })
    if (this.state.current_query !== url+query) {
      fetch(url+query)
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
    return (
      <div className={ "container " + (this.props.isInline ? '' : "content")}>
        <div className="products">
        <div className="products-title">
          <h1 className="highlights-title">{this.props.name || this.props.category || "Our bestsellers"}</h1>
        </div>

          <Row className="items">
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
                return (
                  <Col xl={3} lg={3} md={6} xs={12} key={product.id.asin || product.id}>
                    <div className="item" >
                      <Link to={`/item/${product.id.asin || product.id}`}>
                        <div className="product-img" style={{backgroundImage: `url(${product.imUrl})`}}></div>
                        <div className="product-details">
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
                          <div className="product-name">{product.title}</div>
                        </div>
                      </Link>
                      <button onClick={() => this.props.addItemToCart(product)} className="price-add">
                        <div className="product-price">${product.price}</div>
                        <Icon small className="add-icon">add_shopping_cart</Icon>
                      </button>
                    </div>
                  </Col>
                )
            })}
          </Row>
        </div>
      </div>
    );
  }
}

export default Products;
