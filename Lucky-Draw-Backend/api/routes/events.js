const express = require('express');
const router = express.Router();

const checkAuth = require('../middleware/check-auth');

const EventController = require('../controllers/event')

router.get('/nextEvents', checkAuth.user, EventController.nextEvents)

router.get('/previousEvents', checkAuth.user, EventController.previousEvents)

router.post('/createEvent', checkAuth.admin, EventController.createEvent)

router.patch('/addParticipant/:event_id', checkAuth.user, EventController.addParticipant)

router.get('/computeWinner/:event_id', checkAuth.admin, EventController.computeWinner)

module.exports = router;