const express = require('express');
const router = express.Router();
const mongoose = require('mongoose')

const User = require('../models/user');


router.patch('/addTickets/:user_id',(req,res,next)=>{
    const id = req.params.user_id;
    const tickets = req.body.tickets;
    User.findByIdAndUpdate(id,{$inc:{tickets:tickets}}).exec().then(result=>{
        res.status(200).json({
            message:'Tickets have been added',
            result:result
        });
    }).catch(err=>{
        console.log(err);
        res.status(500).json({
            error:err
        })
    });

    
});
router.post('/addUser',(req,res,next)=>{
    const user = new User({
        _id: new mongoose.Types.ObjectId(),
        name: req.body.name,
        tickets:req.body.tickets
    })

    user.save().then(result=>{
        res.status(201).json({
            message: 'User saved',
            result:result
        })
    }).catch(err=>{
        console.log(err);
        res.status(500).json({
            error:err
        })
    });
});

router.get('/:user_id',(req,res,next)=>{

    const id = req.params.user_id;

    User.findById(id).exec().then(doc=>{
        res.status(200).json(doc)
    }).catch(err=>{
        console.log(err)
        res.status(500).json({
            error:err
        })
    })
});

module.exports = router;