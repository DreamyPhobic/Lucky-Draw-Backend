const mongoose = require('mongoose');

const eventSchema = mongoose.Schema({
    _id:mongoose.Schema.Types.ObjectId,
    Reward: String,
    Time: Number,
    Status: Number,
    Participants: [{type: mongoose.Schema.Types.ObjectId, ref:'User'}],
    Winners: [{type: mongoose.Schema.Types.ObjectId, ref:'User'}]
    
});

module.exports = mongoose.model("Event",eventSchema); 