//Dependencies
import React from 'react';
import { Logo } from '../';
import { Icon } from '../../../common';
import { NavLink } from 'react-router-dom';
//Internals
import './index.css';

const Footer = () => (
  <div className="footer">
    <div className="footer-block1">
      <Logo mode={'dark'} />
      <p>© Copyright Yugabyte 2018</p>
    </div>
    <div className="footer-block2">
      <h6>Categories</h6>
      <ul>
        <li><NavLink to="/Books">Books</NavLink></li>
        <li><NavLink to="/Music">Music</NavLink></li>
        <li><NavLink to="/Beauty">Beauty</NavLink></li>
        <li><NavLink to="/Electronics">Electronics</NavLink></li>
      </ul>
    </div>
    <div className="footer-block3">
      <div className="social">
        <Icon icon="twitter" />
        <Icon icon="facebook" />
        <Icon icon="instagram" />
      </div>
      <div>This app is made with YugaByte DB. <br/>Read more on <NavLink to="http://yugabyte.com">yugabyte.com</NavLink></div>
    </div>
  </div>
)

export default Footer;
