const { Schema, model } = require("mongoose");

const Report = new Schema({
    timestamp: {type: Date, default: Date.now},
    status: Number,
    service: String
})

module.exports = model("Report", Report)