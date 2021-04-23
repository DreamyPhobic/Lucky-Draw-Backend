const express = require('express');
const router = express.Router();

const checkAuth = require('../middleware/check-auth');

const UserController = require('../controllers/user');

router.post("/signup", UserController.user_signup);

router.post("/login", UserController.user_login);

router.patch('/addTickets/:user_id', checkAuth.admin, UserController.add_tickets);

module.exports = router;