using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace NETAPI.Models
{
    public class Tour
    {
        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("_id")]
        public string Id { get; set; }
        [BsonRepresentation(BsonType.String)]
        [BsonElement("name")]
        public string Name { get; set; }
        [BsonRepresentation(BsonType.String)]
        [BsonElement("description")]
        public string Description { get; set; }
        [BsonRepresentation(BsonType.String)]
        [BsonElement("guide")]
        public string Guide { get; set; }
        [BsonRepresentation(BsonType.Array)]
        [BsonElement("cities")]
        public List<string> Cities { get; set; }
        [BsonRepresentation(BsonType.Int32)]
        [BsonElement("allSeats")]
        public int All { get; set; }
        [BsonRepresentation(BsonType.String)]
        [BsonElement("status")]
        public string Status { get; set; }
        [BsonRepresentation(BsonType.Int32)]
        [BsonElement("freeSeats")]
        public int Free { get; set; }
        [BsonRepresentation(BsonType.Int32)]
        [BsonElement("price")]
        public int Price { get; set; }
    }
}
