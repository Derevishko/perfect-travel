using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Driver;
using MongoDB.Driver.GridFS;
using System.IO;
using System.Configuration;


namespace NETAPI.Models
{
    public class MongoContext
    {
        public IMongoDatabase database;
        IGridFSBucket gridFS;

        public MongoContext()
        {
            string connectionString = "mongodb://localhost/TourAgencyDB";
            var connection = new MongoUrlBuilder(connectionString);
            MongoClient client = new MongoClient(connectionString);
            database = client.GetDatabase(connection.DatabaseName);
            gridFS = new GridFSBucket(database);
        }

        public IMongoCollection<Tour> Tours
        {
            get { return database.GetCollection<Tour>("Tours"); }
        }

        public class TourList
        {
            public IEnumerable<Tour> Tours { get; set; }
        }
    }
}
