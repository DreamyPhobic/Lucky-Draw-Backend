const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const { findByIdAndUpdate } = require('../models/event');

const Event = require('../models/event');

router.get('/nextEvents',(req,res,next)=>{
    Event
        .find()
        .where({ Status: 0 })
        .select('_id Reward Time')
        .then(doc => {
            res.status(200).json(doc)
        })
        .catch(err=>{
            console.log(err);
            res.status(500).json({error:err})
        })
})

router.get('/previousEvents',(req,res,next)=>{
    Event
        .find()
        .where({ Status: 1 })
        .select('_id Reward Time Winner')
        .populate('Winner','name')
        .then(doc => {
            res.status(200).json(doc)
        })
        .catch(err=>{
            console.log(err);
            res.status(500).json({error:err})
        })
})

router.post('/',(req,res,next)=>{
    const event = new Event({
        _id: new mongoose.Types.ObjectId(),
        Reward: req.body.Reward,
        Time: req.body.Time,
        Status: req.body.Status
    })

    event.save()
        .then(doc=>{
            res.status(201).json({
                message: 'Event added',
                result: doc
            })
        })
        .catch(err=>{
            console.log(err);
            res.status(500).json({
                error:err
            })
        })
})

router.patch('/addParticipant/:event_id',(req,res,next)=>{
    const id = req.params.event_id;
    const participant_id = req.body.participant_id;
    Event.findById(id,function(err,event){
        if(err){
            return res.status(500).json({
                error:err
            })
        }

        if(!event.Participants.includes(participant_id)){
            event.Participants.push(participant_id);
            event.save().then(doc=>{
                res.status(201).json({
                    message:'Participant Added',
                    result:doc
                })
            })
            .catch(err=>{
                console.log(err);
                res.status(500).json({
                    error:err
                })
            })
        }
        else{
            res.status(409).json({
                message:'Already joined'
            })
        }
    })
})

function computeWinner(event_id){
    Event.findById(event_id,function(err,event){
        if(err){
            console.log(err)
            return;
        }

        const size = event.Participants.size;
        var winner = event.Participants[Math.floor(Math.random()*size)]

        event.Winner = winner

        event.save().then(doc=>{
            console.log('Winner Announced')
            console.log(doc)
        })
        .catch(err=>{
            console.log(err)
        })

    })
}


module.exports = router;