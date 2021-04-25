const mongoose = require('mongoose');

const eventSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    Reward: { type: String, required: true },
    Time: { type: String, required: true },
    Status: { type: Number, default: 0 },  // Status = 0 means Event is ongoing
                                           // Status = 1 means Event finished
    Participants: [{ type: mongoose.Schema.Types.ObjectId, ref: 'User' }],
    Winner: { type: mongoose.Schema.Types.ObjectId, ref: 'User' }

});

module.exports = mongoose.model("Event", eventSchema);